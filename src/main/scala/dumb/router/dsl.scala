package dumb.router

trait Dsl {
  val router = new Router()

  def get(url: String, handler: Handler) = addHandler(router.getRoutes, url, handler)

  def get(url: String, handler: ((Request, Response) => Any)) = addCallback(router.getRoutes, url, handler)

  def post(url: String, handler: Handler) = addHandler(router.postRoutes, url, handler)

  def post(url: String, handler: ((Request, Response) => Any)) = addCallback(router.postRoutes, url, handler)

  private def addHandler(routes: scala.collection.mutable.Map[String, Handler], url: String, handler: Handler) = {
    router.getRoutes(url) = handler
  }

  private def addCallback(routes: scala.collection.mutable.Map[String, Handler], url: String, handler: ((Request, Response) => Any)) = {
    routes(url) = new Handler {
      def handle(request: Request, response: Response) = handler(request, response)
    }
  }
}
