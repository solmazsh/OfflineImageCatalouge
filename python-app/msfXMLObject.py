import xml.etree.ElementTree as ET

class msfXmlObject(object):

    def __init__(self, fileName):
        self.tree = ET.parse(fileName)
        self.root = self.tree.getroot()
    
    def find_item(self, itemName):
        d = ['empty']
        for child in self.root:
            i = 0
            for key in child.attrib:
                if itemName == child.attrib[key]:
                    d.append(child)
                    continue
                for child1 in child:
                    
                    if i < 1:
                        if itemName ==  child1.text:
                            d.append(child)
                            i += 1

        return d    

