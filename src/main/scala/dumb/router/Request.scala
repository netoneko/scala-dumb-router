package dumb.router

import javax.servlet.http.HttpServletRequest

trait Request {
  def req: HttpServletRequest

  def uri = req getRequestURI
}