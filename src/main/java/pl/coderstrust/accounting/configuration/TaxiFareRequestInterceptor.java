package pl.coderstrust.accounting.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TaxiFareRequestInterceptor
    extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler) {

    HttpServletRequest requestCacheWrapperObject
        = new ContentCachingRequestWrapper(request);
    requestCacheWrapperObject.getParameterMap();
    // Read inputStream from requestCacheWrapperObject and log it
    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      Exception ex) {
    //
  }
}