package dumb.router

import dumb.router.impl.NotFoundHandler
import scala.util.matching.Regex

object Router {
  val getRoutes = scala.collection.mutable.Map[String, Handler]()
  val postRoutes = scala.collection.mutable.Map[String, Handler]()

  def getRoute = route(getRoutes, _: Request, _: Response)

  def postRoute = route(postRoutes, _: Request, _: Response)

  private def route(routes: scala.collection.mutable.Map[String, Handler], request: Request, response: Response) {
    val route = routes.find {
      case (key, _) => matchKey(key, request)
    }

    val handler = route.size match {
      case 1 => route.head._2
      case _ => new NotFoundHandler()
    }

    response write (handler handle(request, response))

    println(s"${response.code} ${request.uri}")
  }

  def buildPattern(url: String): Regex = {
    new Regex( """/hello/(\w*)""", "name")
  }

  def matchKey(key: String, request: Request): Boolean = {
    if (key.contains(":")) {
      val pattern = buildPattern(key)

      pattern.findFirstMatchIn(request.uri) match {
        case Some(result: Regex.Match) => {
          request.params("name") = Array(result.group("name"))
          true
        }
        case _ => false
      }
    }
    else {
      request.uri == key
    }
  }
}
