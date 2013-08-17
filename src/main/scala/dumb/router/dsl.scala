package dumb.router

package object dsl {
  def get(url: String, handler: Handler) = addHandler(Router.getRoutes, url, handler)

  def get(url: String, handler: ((Request, Response) => Any)) = addCallback(Router.getRoutes, url, handler)

  def post(url: String, handler: Handler) = addHandler(Router.postRoutes, url, handler)

  def post(url: String, handler: ((Request, Response) => Any)) = addCallback(Router.postRoutes, url, handler)

  private def addHandler(routes: scala.collection.mutable.Map[String, Handler], url: String, handler: Handler) = {
    Router.getRoutes(url) = handler
  }

  private def addCallback(routes: scala.collection.mutable.Map[String, Handler], url: String, handler: ((Request, Response) => Any)) = {
    routes(url) = new Handler {
      def handle(request: Request, response: Response) = handler(request, response)
    }
  }
}
