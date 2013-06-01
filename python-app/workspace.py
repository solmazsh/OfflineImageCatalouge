import xml.etree.ElementTree as ET

tree = ET.parse("sample.xml")
root = tree.getroot()
for child in root:
    print child.tag, child.attrib
    for child1 in child:
        print child1.text