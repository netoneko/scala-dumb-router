package dumb.router

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

package object dsl {
  def get(url: String, handler: Handler) = {
    Router.routes(url) = handler
  }

  def get(url: String, handler: ((HttpServletRequest,HttpServletResponse) => Any)) = {
    Router.routes(url) = new Handler {
      def handle(request: HttpServletRequest, response: HttpServletResponse) = handler(request, response)
    }
  }
}
