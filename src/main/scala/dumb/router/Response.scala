package dumb.router

import javax.servlet.http.HttpServletResponse

trait Response {
  def res: HttpServletResponse

  def setContentType(value: String) = res setContentType value

  def setCharacterEncoding(value: String) = res setCharacterEncoding value

  def write(data: Any) = res.getWriter write data.toString

  def setStatus(i: Int) = res setStatus i

  def code = res getStatus
}