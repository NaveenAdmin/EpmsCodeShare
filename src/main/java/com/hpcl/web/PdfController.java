package com.hpcl.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hpcl.csv.*;
import com.hpcl.dao.ConfigurationDao;
import com.hpcl.persistence.AppParameters;
import com.hpcl.utill.SchemaDef;

/**
 * A Spring controller that allows the users to download a PDF document
 * generated by the iText library.
 * 
 */
@Controller
public class PdfController {
    /**
	 * Handle request to download a PDF document 
	 */
	@Autowired
	ConfigurationDao threeFieldDao;
	@RequestMapping(value="/downloadPDF.pdf", method = RequestMethod.GET)
	public ModelAndView downloadExcel(@ModelAttribute AppParameters parameters) {
		// create some sample data
		List<AppParameters> appParametersListss = threeFieldDao.getOnlyDasboardSearchfilters(SchemaDef.TR_DASHBOARD, parameters.getLocationID(), parameters.getDeviceId(), parameters.getFromdatetime(), parameters.getTodatetime(), parameters);
		List<PdfParameters> listdata = new ArrayList<PdfParameters>();
		for(AppParameters appParameters: appParametersListss){
			listdata.add(new PdfParameters(appParameters.getLocationName(), appParameters.getDeviceName(), appParameters.getEarthpitName(),
					appParameters.getVoltage(), appParameters.getReceivedDate()));
		}
		

		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "listdata", listdata);
	}
}