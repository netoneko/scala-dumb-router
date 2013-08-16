package dumb.router

import dumb.router.impl.NotFoundHandler

object Router {
  val routes = scala.collection.mutable.Map[String, Handler]()

  def route(request: Request, response: Response) {
    response write (routes get request.uri match {
      case Some(handler) => handler
      case None => new NotFoundHandler()
    }).handle(request, response)

    println(s"${response.code} ${request.uri}")
  }
}
