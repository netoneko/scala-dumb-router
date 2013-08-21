package dumb.router

import dumb.router.impl.NotFoundHandler
import scala.util.matching.Regex

object Router {
  val getRoutes = scala.collection.mutable.Map[String, Handler]()
  val postRoutes = scala.collection.mutable.Map[String, Handler]()

  def getRoute = route("GET", getRoutes, _: Request, _: Response)

  def postRoute = route("POST", postRoutes, _: Request, _: Response)

  private def route(method: String, routes: scala.collection.mutable.Map[String, Handler], request: Request, response: Response) {
    val route = routes.find {
      case (key, _) => matchRoute(key, request)
    }

    val handler = if (route.isEmpty) new NotFoundHandler() else route.head._2
    response write (handler handle(request, response))

    println(s"$method ${response.code} ${request.uri}")
  }

  def buildPattern(urlPattern: String, keys: String*): Regex = {
    val pattern = keys.foldLeft(urlPattern) {
      (result, key) => result.replace(s":$key", "(\\w*)")
    }

    new Regex(pattern, keys: _*)
  }

  def matchRoute(urlPattern: String, request: Request): Boolean = {
    if (urlPattern.contains(":")) {
      val keys = new Regex( """(:\w*)""").findAllIn(urlPattern).map {
        k => k.substring(1)
      }.toSeq
      val pattern = buildPattern(urlPattern, keys: _*)

      pattern.findFirstMatchIn(request.uri) match {
        case Some(result: Regex.Match) => {
          keys.foreach {
            key => request.params(key) = Array(result.group(key))
          }

          true
        }
        case _ => false
      }
    }
    else {
      request.uri == urlPattern
    }
  }
}
