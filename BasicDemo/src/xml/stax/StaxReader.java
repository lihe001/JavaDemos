package xml.stax;

import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;


public class StaxReader {

    public static void main(String[] args) {
        StaxReader.readByStream();
        System.out.println("========�������ķָ���==========");
        StaxReader.readByEvent();
    }

    //��ģʽ
    public static void readByStream() {
        String xmlFile = "books.xml";
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader streamReader = null;
        try {
            streamReader = factory.createXMLStreamReader(new FileReader(xmlFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        // ����ָ�����
        try {
            while (streamReader.hasNext()) {
                int event = streamReader.next();
                // �����Ԫ�صĿ�ʼ
                if (event == XMLStreamConstants.START_ELEMENT) {
                    // �г������鼮����
                    if ("title".equalsIgnoreCase(streamReader.getLocalName())) {
                        System.out.println("title:" + streamReader.getElementText());
                    }
                }
            }
            streamReader.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }


    // �¼�ģʽ
    public static void readByEvent() {
        String xmlFile = "books.xml";
        XMLInputFactory factory = XMLInputFactory.newInstance();
        boolean titleFlag = false;
        try {
            // �������ڵ��������¼���ȡ������
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(xmlFile));
            // ����Event������
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                // ����¼�������Ԫ�صĿ�ʼ
                if (event.isStartElement()) {
                    // ת���ɿ�ʼԪ���¼�����
                    StartElement start = event.asStartElement();
                    // ��ӡԪ�ر�ǩ�ı�������

                    String name = start.getName().getLocalPart();
                    //System.out.print(start.getName().getLocalPart());
                    if (name.equals("title")) {
                        titleFlag = true;
                        System.out.print("title:");
                    }

                    // ȡ����������
                    Iterator attrs = start.getAttributes();
                    while (attrs.hasNext()) {
                        // ��ӡ����������Ϣ
                        Attribute attr = (Attribute) attrs.next();
                        //System.out.print(":" + attr.getName().getLocalPart() + "=" + attr.getValue());
                    }
                    //System.out.println();
                }
                //���������
                if (event.isCharacters()) {
                    String s = event.asCharacters().getData();
                    if (null != s && s.trim().length() > 0 && titleFlag) {
                        System.out.println(s.trim());
                    }
                }
                //����¼�������Ԫ�صĽ���
                if (event.isEndElement()) {
                    EndElement end = event.asEndElement();
                    String name = end.getName().getLocalPart();
                    if (name.equals("title")) {
                        titleFlag = false;
                    }
                }
            }
            eventReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
