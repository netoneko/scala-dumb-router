package dumb.router

import dumb.router.impl.NotFoundHandler

object Router {
  val getRoutes = scala.collection.mutable.Map[String, Handler]()
  val postRoutes = scala.collection.mutable.Map[String, Handler]()

  def getRoute = route(getRoutes, _: Request, _: Response)

  def postRoute = route(postRoutes, _: Request, _: Response)

  private def route(routes: scala.collection.mutable.Map[String, Handler], request: Request, response: Response) {
    response write (routes get request.uri match {
      case Some(handler) => handler
      case None => new NotFoundHandler()
    }).handle(request, response)

    println(s"${response.code} ${request.uri}")
  }
}
