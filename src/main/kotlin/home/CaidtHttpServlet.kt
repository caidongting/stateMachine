package home

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CaidtHttpServlet : HttpServlet() {

  override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    doPost(req, resp)
  }

  override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
    Gate.start()
  }

}