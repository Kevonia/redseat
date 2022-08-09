package com.kovecmedia.redseat.service;

import java.io.File;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.kovecmedia.redseat.doa.CompanyRepository;
import com.kovecmedia.redseat.doa.ManifestsItemRepository;
import com.kovecmedia.redseat.doa.ManifestsRepository;
import com.kovecmedia.redseat.entity.Manifest;
import com.kovecmedia.redseat.entity.ManifestItems;

@Service
public class ManifestServiceIml implements ManifestService {

	@Autowired
	ManifestsItemRepository manifestsItemRepository;
	@Autowired
	ManifestsRepository manifestsRepository;
	
	@Autowired
	CompanyRepository companyRepository;

	public static final String xmlFilePath = "C:\\temp\\xmlfile.xml";
  
	public static final int MAX_LENGTH =8;
	
	public static final String DEFAULT_SHIPPER ="Amazon";
	
	@Override
	public String createMinfestXML(long Id) {

		try {

			Manifest manifest = manifestsRepository.getOne(Id);

			List<ManifestItems> items = manifestsItemRepository.findByManifestId(manifest);

			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			Element root = document.createElement("Awbolds");
			document.appendChild(root);

			Attr attr = document.createAttribute("xmlns:xsi");
			attr.setValue("http://www.w3.org/2001/XMLSchema-instance");
			root.setAttributeNode(attr);

			Element masterBol = document.createElement("Master_bol");
			root.appendChild(masterBol);

			Element customsOfficeCode = document.createElement("Customs_office_code");
			customsOfficeCode.appendChild(document.createTextNode("JMKIN"));
			masterBol.appendChild(customsOfficeCode);

			Element voyageNumber = document.createElement("Voyage_number");
			voyageNumber.appendChild(document.createTextNode("BW8038"));
			masterBol.appendChild(voyageNumber);

			Element dateOfDeparture = document.createElement("Date_of_departure");
			dateOfDeparture.appendChild(document.createTextNode(dt1.format(manifest.getCreated_at())));
			masterBol.appendChild(dateOfDeparture);

			Element referenceNumber = document.createElement("Reference_number");
			referenceNumber.appendChild(document.createTextNode(manifest.getMasterAwb()));
			masterBol.appendChild(referenceNumber);

			for (ManifestItems item : items) {
				
				String mID="RSC".concat(padLeftZeros(item.getId().toString(),  MAX_LENGTH - item.getId().toString().length()));
                String wgth = Double.toString(DoubleRounder.round(Double.parseDouble(item.getWtkg()),3)); 
                String Seller = Optional.ofNullable(item.getShipper())
                .orElse(DEFAULT_SHIPPER);
				Element bolSegment = document.createElement("Bol_segment");
				root.appendChild(bolSegment);

				Element bolId = document.createElement("Bol_id");
				bolSegment.appendChild(bolId);

				Element bolReference = document.createElement("Bol_reference");
				bolReference.appendChild(document.createTextNode(mID));
				bolId.appendChild(bolReference);

				Element lineNumber = document.createElement("Line_number");
				lineNumber.appendChild(document.createTextNode(item.getId().toString()));
				bolId.appendChild(lineNumber);

				Element bolNature = document.createElement("Bol_nature");
				bolNature.appendChild(document.createTextNode("23"));
				bolId.appendChild(bolNature);

				Element bolTypeCode = document.createElement("Bol_type_code");
				bolTypeCode.appendChild(document.createTextNode("AWB"));
				bolId.appendChild(bolTypeCode);

				Element transportInformation = document.createElement("Transport_information");
				bolSegment.appendChild(transportInformation);

				Element carrier = document.createElement("Carrier");
				transportInformation.appendChild(carrier);

				Element carrierCode = document.createElement("Carrier_code");
				carrierCode.appendChild(document.createTextNode("BW"));
				carrier.appendChild(carrierCode);

				Element shippingAgent = document.createElement("Shipping_Agent");
				transportInformation.appendChild(shippingAgent);

				Element shippingAgentCode = document.createElement("Shipping_Agent_code");
				shippingAgentCode.appendChild(document.createTextNode("0029516650000"));
				shippingAgent.appendChild(shippingAgentCode);

				Element vesselLoadingCode = document.createElement("Vessel_loading_code");
				vesselLoadingCode.appendChild(document.createTextNode("USMIA"));
				transportInformation.appendChild(vesselLoadingCode);

				Element vesselDischargeCode = document.createElement("Vessel_discharge_code");
				vesselDischargeCode.appendChild(document.createTextNode("JMKIN"));
				transportInformation.appendChild(vesselDischargeCode);

				Element loadunloadplace = document.createElement("Load_unload_place");
				bolSegment.appendChild(loadunloadplace);

				Element placeOfLoadingCode = document.createElement("Place_of_loading_code");
				placeOfLoadingCode.appendChild(document.createTextNode("USMIA"));
				loadunloadplace.appendChild(placeOfLoadingCode);

				Element placeOfUnloadingCode = document.createElement("Place_of_unloading_code");
				placeOfUnloadingCode.appendChild(document.createTextNode("JMKIN"));
				loadunloadplace.appendChild(placeOfUnloadingCode);

				Element tradersSegment = document.createElement("Traders_segment");
				bolSegment.appendChild(tradersSegment);

				Element exporter = document.createElement("Exporter");
				tradersSegment.appendChild(exporter);

				Element exporterName = document.createElement("Exporter_name");
				exporterName.appendChild(document.createTextNode(Seller));
				exporter.appendChild(exporterName);

				Element exporterAddress = document.createElement("Exporter_address");
				exporterAddress.appendChild(document.createTextNode("MIAMI"));
				exporter.appendChild(exporterAddress);

				Element consignee = document.createElement("Consignee");
				tradersSegment.appendChild(consignee);

				Element consigneeName = document.createElement("Consignee_name");
				consigneeName.appendChild(document.createTextNode(item.getConsignee()));
				consignee.appendChild(consigneeName);

				Element consigneeAddress = document.createElement("Consignee_address");
				consigneeAddress.appendChild(document.createTextNode(companyRepository.findById(1L).get().getBaseAddress() ));
				consignee.appendChild(consigneeAddress);

				Element goodsSegment = document.createElement("Goods_segment");
				bolSegment.appendChild(goodsSegment);

				Element numberofPackages = document.createElement("Number_of_packages");
				numberofPackages.appendChild(document.createTextNode("1"));
				goodsSegment.appendChild(numberofPackages);

				Element packageTypeCode = document.createElement("Package_type_code");
				packageTypeCode.appendChild(document.createTextNode("PK"));
				goodsSegment.appendChild(packageTypeCode);

				Element grossmass = document.createElement("Gross_mass");
				grossmass.appendChild(document.createTextNode(wgth));
				goodsSegment.appendChild(grossmass);

				Element ShippingMarks = document.createElement("Shipping_marks");
				ShippingMarks.appendChild(document.createTextNode("AS ADDRESSED"));
				goodsSegment.appendChild(ShippingMarks);

				Element goodsdescription = document.createElement("Goods_description");
				goodsdescription.appendChild(document.createTextNode(item.getDescription()));
				goodsSegment.appendChild(goodsdescription);

				Element volumeinCubicMeters = document.createElement("Volume_in_cubic_meters");
				volumeinCubicMeters.appendChild(document.createTextNode("0"));
				goodsSegment.appendChild(volumeinCubicMeters);

				Element NumofctnforthisBol = document.createElement("Num_of_ctn_for_this_bol");
				NumofctnforthisBol.appendChild(document.createTextNode("0"));
				goodsSegment.appendChild(NumofctnforthisBol);

				Element valueSegment = document.createElement("Value_segment");
				bolSegment.appendChild(valueSegment);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);

			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);

			transformer.transform(domSource, result);
			return writer.toString();

			// System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		return "NONE";
	}

	@Override
	public void createMinfestTest() {
		// TODO Auto-generated method stub
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			Element root = document.createElement("Awbolds");
			document.appendChild(root);

			Attr attr = document.createAttribute("xmlns:xsi");
			attr.setValue("http://www.w3.org/2001/XMLSchema-instance");
			root.setAttributeNode(attr);

			Element masterBol = document.createElement("Master_bol");
			root.appendChild(masterBol);

			Element customsOfficeCode = document.createElement("Customs_office_code");
			customsOfficeCode.appendChild(document.createTextNode("JMKIN"));
			masterBol.appendChild(customsOfficeCode);

			Element voyageNumber = document.createElement("Voyage_number");
			voyageNumber.appendChild(document.createTextNode("BW8040"));
			masterBol.appendChild(voyageNumber);

			Element dateOfDeparture = document.createElement("Date_of_departure");
			dateOfDeparture.appendChild(document.createTextNode("2022-05-01"));
			masterBol.appendChild(dateOfDeparture);

			Element referenceNumber = document.createElement("Reference_number");
			referenceNumber.appendChild(document.createTextNode("106-90817624"));
			masterBol.appendChild(referenceNumber);

			Element bolSegment = document.createElement("Bol_segment");
			root.appendChild(bolSegment);

			Element bolId = document.createElement("Bol_id");
			bolSegment.appendChild(bolId);

			Element bolReference = document.createElement("Bol_reference");
			bolReference.appendChild(document.createTextNode("FRS314764"));
			masterBol.appendChild(bolReference);

			Element lineNumber = document.createElement("Line_number");
			lineNumber.appendChild(document.createTextNode("1"));
			bolId.appendChild(lineNumber);

			Element bolNature = document.createElement("Bol_nature");
			bolNature.appendChild(document.createTextNode("23"));
			bolId.appendChild(bolNature);

			Element bolTypeCode = document.createElement("Bol_type_code");
			bolTypeCode.appendChild(document.createTextNode("740"));
			bolId.appendChild(bolTypeCode);

			Element transportInformation = document.createElement("Transport_information");
			bolSegment.appendChild(transportInformation);

			Element carrier = document.createElement("Carrier");
			transportInformation.appendChild(carrier);

			Element carrierCode = document.createElement("Carrier_code");
			carrierCode.appendChild(document.createTextNode("BW"));
			carrier.appendChild(carrierCode);

			Element shippingAgent = document.createElement("Shipping_Agent");
			transportInformation.appendChild(shippingAgent);

			Element shippingAgentCode = document.createElement("Shipping_Agent_code");
			shippingAgent.appendChild(document.createTextNode("1023099150002"));
			shippingAgent.appendChild(shippingAgentCode);

			Element vesselLoadingCode = document.createElement("Vessel_loading_code");
			vesselLoadingCode.appendChild(document.createTextNode("USMIA"));
			transportInformation.appendChild(vesselLoadingCode);

			Element vesselDischargeCode = document.createElement("Vessel_discharge_code");
			vesselDischargeCode.appendChild(document.createTextNode("JMKIN"));
			transportInformation.appendChild(vesselDischargeCode);

			Element loadunloadplace = document.createElement("Load_unload_place");
			bolSegment.appendChild(loadunloadplace);

			Element placeOfLoadingCode = document.createElement("Place_of_loading_code");
			placeOfLoadingCode.appendChild(document.createTextNode("USMIA"));
			transportInformation.appendChild(vesselLoadingCode);

			Element placeOfUnloadingCode = document.createElement("Place_of_unloading_code");
			placeOfUnloadingCode.appendChild(document.createTextNode("JMKIN"));
			transportInformation.appendChild(vesselDischargeCode);

			Element tradersSegment = document.createElement("Traders_segment");
			root.appendChild(tradersSegment);

			Element exporter = document.createElement("Exporter");
			tradersSegment.appendChild(exporter);

			Element exporterName = document.createElement("Exporter_name");
			exporterName.appendChild(document.createTextNode("JMKIN"));
			exporter.appendChild(exporterName);

			Element exporterAddress = document.createElement("Exporter_address");
			exporterAddress.appendChild(document.createTextNode("MIAMI"));
			exporter.appendChild(exporterAddress);

			Element consignee = document.createElement("Consignee");
			tradersSegment.appendChild(consignee);

			Element consigneeName = document.createElement("Consignee_name");
			consigneeName.appendChild(document.createTextNode("HORACE HUGGINS C/O FRS Couriers"));
			consignee.appendChild(consigneeName);

			Element consigneeAddress = document.createElement("Consignee_address");
			consigneeAddress.appendChild(document.createTextNode("Shop 7, 127 Hagley Park Rd, , Kingston, St. Andrew"));
			consignee.appendChild(consigneeAddress);

			Element goodsSegment = document.createElement("Goods_segment");
			bolSegment.appendChild(goodsSegment);

			Element numberofPackages = document.createElement("Number_of_packages");
			numberofPackages.appendChild(document.createTextNode("1"));
			goodsSegment.appendChild(numberofPackages);

			Element packageTypeCode = document.createElement("Package_type_code");
			consigneeAddress.appendChild(document.createTextNode("PK"));
			goodsSegment.appendChild(packageTypeCode);

			Element grossmass = document.createElement("Gross_mass");
			grossmass.appendChild(document.createTextNode("1.02"));
			goodsSegment.appendChild(grossmass);

			Element ShippingMarks = document.createElement("Shipping_marks");
			ShippingMarks.appendChild(document.createTextNode("AS ADDRESSED"));
			goodsSegment.appendChild(ShippingMarks);

			Element goodsdescription = document.createElement("Goods_description");
			goodsdescription.appendChild(document.createTextNode("Merchandise"));
			goodsSegment.appendChild(goodsdescription);

			Element volumeinCubicMeters = document.createElement("Volume_in_cubic_meters");
			volumeinCubicMeters.appendChild(document.createTextNode("0"));
			goodsSegment.appendChild(volumeinCubicMeters);

			Element NumofctnforthisBol = document.createElement("Num_of_ctn_for_this_bol");
			NumofctnforthisBol.appendChild(document.createTextNode("0"));
			goodsSegment.appendChild(NumofctnforthisBol);

			Element valueSegment = document.createElement("Value_segment");
			bolSegment.appendChild(valueSegment);

			// create the xml fileGoods_segment
			// transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public String padLeftZeros(String inputString, int length) {
		
	
		if (inputString.length() >= length) {
			return inputString;
		}
		StringBuilder sb = new StringBuilder();
		while (sb.length() < length-- ) {
			sb.append('0');
		}
		sb.append(inputString);

		return sb.toString();
	}

}
