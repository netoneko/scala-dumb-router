package dumb.router

import scala.xml.NodeSeq

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class NotFoundHandler extends Handler {
  override def handle(request: HttpServletRequest, response: HttpServletResponse) {
    val responseBody: NodeSeq = <html><body><h1>404</h1></body></html>
    response.getWriter.write(responseBody.toString)
  }
}
