package dumb.router

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

trait Handler {
  def handle(request: HttpServletRequest, response: HttpServletResponse)
}
