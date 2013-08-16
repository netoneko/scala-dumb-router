package dumb.router

import javax.servlet.http.HttpServletResponse

case class Response(res: HttpServletResponse) {
  def write(data: Any) = res.getWriter write data.toString

  def setStatus(i:Int) = res setStatus i

  def code = res getStatus
}
