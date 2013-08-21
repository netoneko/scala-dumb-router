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

  def matchRoute(urlPattern: String, request: Request): Boolean = {
    if (urlPattern.contains(":")) {
      buildPattern(urlPattern, request)
    }
    else {
      request.uri == urlPattern
    }
  }

  def buildPattern(urlPattern: String, request: Request): Boolean = {
    val keys = new Regex( """(:\w*)""").findAllIn(urlPattern).map {
      k => k.substring(1)
    }.toSeq

    val pattern = new Regex(keys.foldLeft(urlPattern) {
      (result, key) => result.replace(s":$key", "(\\w*)")
    }, keys: _*)

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

}
