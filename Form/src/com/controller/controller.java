package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.*;

import com.vo.*;

@Controller
public class controller {

	@Autowired
	regdao d;

	@RequestMapping(value = "/insert2.html", method = RequestMethod.GET)
	public ModelAndView insert(HttpServletRequest req) {
		String s = req.getParameter("fn");
		String s1 = req.getParameter("ln");
		String s2 = req.getParameter("mn");
		String s3 = req.getParameter("add");
		String s4 = req.getParameter("email");
		String s5 = req.getParameter("contact");	
		
		regvo2 v = new regvo2();
		v.setFn(s);
		v.setLn(s1);
		v.setMn(s2);
		v.setAdd(s3);
		v.setEmail(s4);
		v.setContact(s5);
      
		
			try{
				d.insert(v);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			String s6 = "insert done here ";
			return new ModelAndView("welcome", "msg", s6);
	}
		
		
		

	@Autowired
	regdao1 d1;

	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public ModelAndView search(HttpServletRequest req, HttpSession Session) {
		String s = req.getParameter("fn");

		regvo2 v = new regvo2();
		v.setFn(s);
		
			List l = d1.search(v);
			Session.setAttribute("list", l);
		
		//String s4 = "searching done baby!!!";
		return new ModelAndView("welcome1");

	}

	@RequestMapping(value = "/update.html", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest req, HttpSession Session) {

		String s = req.getParameter("s1");
		int i = Integer.parseInt(s);

		regvo2 v = new regvo2();
		v.setId(i);

		List l = d1.update(v);

		return new ModelAndView("update", "list", l);

	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		String s = req.getParameter("fn");
		String s1 = req.getParameter("ln");
		String s2 = req.getParameter("mn");
		String s3 = req.getParameter("add");
		String s4 = req.getParameter("email");
		String s5 = req.getParameter("contact");
		

		regvo2 v = new regvo2();
		v.setId(id);
		v.setFn(s);
		v.setLn(s1);
		v.setMn(s2);
		v.setAdd(s3);
		v.setEmail(s4);
		v.setContact(s5);

		
			d1.edit(v);
		
		String s6 = "insert done";
		return new ModelAndView("welcome", "msg", s6);

	}
}
