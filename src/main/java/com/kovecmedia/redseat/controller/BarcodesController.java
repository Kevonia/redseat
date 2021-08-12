package com.kovecmedia.redseat.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.kovecmedia.redseat.payload.respond.PackageLabel;
import com.kovecmedia.redseat.service.BarCodeServiceImpl;
import com.kovecmedia.redseat.service.EmailService;
import com.lowagie.text.DocumentException;


@Controller
@RequestMapping("barcode")
public class BarcodesController {

	@Autowired
	 BarCodeServiceImpl barCodeService;

	@Autowired
	private org.thymeleaf.spring5.SpringTemplateEngine templateEngine;
	@Autowired
	EmailService  emailService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		PackageLabel barcode = barCodeService.genarateLable(46);
		
		modelMap.put("package", barcode);
		modelMap.put("qrtop", "data:image/png;base64,"+Base64.getEncoder().encodeToString(barcode.getQrTop()) );
		modelMap.put("qrpackage",  "data:image/png;base64,"+Base64.getEncoder().encodeToString(barcode.getQrPackage()));

		try {
			emailService.sendLabel(barcode);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return "lables/package";
	}

	
	private String generatePdfFromHtml(String html, String name) throws IOException, DocumentException {
		String outputFolder = "D:\\"+name + ".pdf";
		OutputStream outputStream = new FileOutputStream(outputFolder);

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		renderer.layout();
		renderer.createPDF(outputStream);

		outputStream.close();

		return outputFolder;
	}



}