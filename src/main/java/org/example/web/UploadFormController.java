package org.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class UploadFormController implements HandlerExceptionResolver
{
  @RequestMapping(method = RequestMethod.GET)
  public String showForm()
  {
    return "form";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String processForm(MultipartFile file, Model model)
  {
    model.addAttribute("file", "You uploaded " + file.getName() + ".");

    return showForm();
  }

  @Override
  public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)
  {
    if (e instanceof MaxUploadSizeExceededException)
    {
      return new ModelAndView(showForm(), "error", "File size must be less than 1 byte.");
    }

    return new ModelAndView(showForm());
  }
}
