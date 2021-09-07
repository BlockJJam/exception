package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {


        log.info("call resolver ", ex);
        try{
            if (ex instanceof IllegalArgumentException) {
                // 예외를 꿀꺽 삼키고 새롭게 정의한 예외에 대한 정상 응답을 전달
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();// 빈 깡통으로 보내면 정상흐름대로 서블릿에 넘김
                // View정보를 지정해줘도 그 뷰를 렌더링해서 보내준다
            }

            /*if(ex ..){
                response.getWriter().println("{'abc':'asds'}");
            }
            이런식으로 API응답을 처리할 수도 있긴 있다*/
        }catch(IOException e){
            log.error("resolver ex",e);
        }

        return null;
    }
}
