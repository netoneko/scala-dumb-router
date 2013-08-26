package dumb.router

import dumb.router.impl.NotFoundHandler
import scala.util.matching.Regex

class Router {
  val getRoutes = scala.collection.mutable.Map[String, Handler]()
  val postRoutes = scala.collection.mutable.Map[String, Handler]()
  val putRoutes = scala.collection.mutable.Map[String, Handler]()
  val deleteRoutes = scala.collection.mutable.Map[String, Handler]()

  def getRoute = route("GET", getRoutes, _: Request, _: Response)

  def postRoute = route("POST", postRoutes, _: Request, _: Response)

  def putRoute = route("PUT", putRoutes, _: Request, _: Response)

  def deleteRoute = route("DELETE", deleteRoutes, _: Request, _: Response)

  private def route(method: String, routes: scala.collection.mutable.Map[String, Handler], request: Request, response: Response) {
    val route = routes.find {
      case (key, _) => matchRoute(key, request)
    }

    val handler = if (route.isEmpty) new NotFoundHandler() else route.head._2
    response write (handler handle(request, response))

    println(s"$method ${response.code} ${request.uri}")
  }

  private def matchRoute(urlPattern: String, request: Request): Boolean = {
    if (urlPattern.contains(":")) {
      buildPattern(urlPattern, request)
    }
    else {
      request.uri == urlPattern
    }
  }

  private val matcherCache = scala.collection.mutable.Map[String, (Seq[String], Regex)]()

  private def buildPattern(urlPattern: String, request: Request): Boolean = {
    val (keys, pattern) = matcherCache.getOrElseUpdate(urlPattern, {
      val paramNames = new Regex( """(:\w*)""").findAllIn(urlPattern).toSeq
      val keys = paramNames.map { k => k.substring(1) }.toSeq

      val pattern = new Regex(paramNames.foldLeft(urlPattern) {
        (result, key) => result.replace(key, "(\\w*)")
      }, keys: _*)

      (keys, pattern)
    })

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
