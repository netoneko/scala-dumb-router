package dumb.router

package object dsl {
  def get(url: String, handler: Handler) = {
    Router.routes(url) = handler
  }

  def get(url: String, handler: ((Request, Response) => Any)) = {
    Router.routes(url) = new Handler {
      def handle(request: Request, response: Response) = handler(request, response)
    }
  }
}
