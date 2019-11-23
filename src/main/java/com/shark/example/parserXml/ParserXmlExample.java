package com.shark.example.parserXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class ParserXmlExample {

    public static void main(String argv[]) {
        String xml = " <soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <soap:Body>\n" +
                "      <ns2:getSalesOrdersResponse xmlns:ns2=\"http://service.webservice.fuyaogroup.com/\">\n" +
                "         <return> \n" +
                "            <customerName>德国 BMW AG</customerName>\n" +
                "            <forecastQuantity>80</forecastQuantity>\n" +
                "            <itemDescription>宝马G20右前门半钢化夹层玻璃（总成）</itemDescription>\n" +
                "            <itemNumber>76960LDR02001M</itemNumber>\n" +
                "            <lastUpdateDate>2019-04-15</lastUpdateDate>\n" +
                "            <month>2019-12</month>\n" +
                "            <organization>YM-SET</organization>\n" +
                "            <productDefaultOrg>G4</productDefaultOrg>\n" +
                "         </return>\n" +
                "         <return>\n" +
                "            <customerName>Hutchinson Industrias Del Caucho</customerName>\n" +
                "            <forecastQuantity>8400</forecastQuantity>\n" +
                "            <itemDescription>哈金森HJB右后三角钢化玻璃（SG）</itemDescription>\n" +
                "            <itemNumber>00132TDR08001</itemNumber>\n" +
                "            <lastUpdateDate>2019-04-22</lastUpdateDate>\n" +
                "            <month>2019-12</month>\n" +
                "            <organization>YMSET</organization>\n" +
                "            <productDefaultOrg>G4</productDefaultOrg>\n" +
                "         </return>\n" +
                "      </ns2:getSalesOrdersResponse>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>\n";


        DocumentBuilder builder = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(xml));
            Document document = builder.parse(inputSource);
            NodeList list = document.getElementsByTagName("return");
            System.out.println("list.length(): " + list.getLength());
            for(int i = 0; i < list.getLength(); i ++) {
                Element element = (Element) list.item(i);
                String customerName = element.getElementsByTagName("customerName").item(0)
                        .getFirstChild().getNodeValue();
                System.out.println("customerName:" + customerName);

                String forecastQuantity = element.getElementsByTagName("forecastQuantity").item(0)
                        .getFirstChild().getNodeValue();
                System.out.println("forecastQuantity:" + forecastQuantity);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
