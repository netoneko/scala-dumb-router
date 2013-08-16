package dumb.router.impl

import javax.servlet.http.HttpServletResponse
import dumb.router.Response

case class RealResponse(res: HttpServletResponse) extends Response
