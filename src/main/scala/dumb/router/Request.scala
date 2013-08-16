package dumb.router

import javax.servlet.http.HttpServletRequest

case class Request(req: HttpServletRequest) {
  def uri = req getRequestURI
}
