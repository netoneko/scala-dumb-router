package dumb.router

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import dumb.router.impl.{RealRequest,RealResponse}

class Servlet extends HttpServlet with Dsl {
  override def doGet(req: HttpServletRequest, res: HttpServletResponse) {
    getRequest(RealRequest(req), RealResponse(res))
  }

  override def doPost(req: HttpServletRequest, res: HttpServletResponse) {
    postRequest(RealRequest(req), RealResponse(res))
  }

  override def doPut(req: HttpServletRequest, res:HttpServletResponse) {
    putRequest(RealRequest(req), RealResponse(res))
  }

  override def doDelete(req: HttpServletRequest, res:HttpServletResponse) {
    deleteRequest(RealRequest(req), RealResponse(res))
  }

  def getRequest(req: Request, res: Response) {
    setBasicSetting(res)
    router.getRoute(req, res)
  }

  def postRequest(req: Request, res: Response) {
    setBasicSetting(res)
    router.postRoute(req, res)
  }

  def putRequest(req: Request, res: Response) {
    setBasicSetting(res)
    router.putRoute(req, res)
  }

  def deleteRequest(req: Request, res: Response) {
    setBasicSetting(res)
    router.deleteRoute(req, res)
  }

  protected def setBasicSetting(res: Response) {
    res setStatus 200
    res setContentType "text/html"
    res setCharacterEncoding "UTF-8"
  }
}
