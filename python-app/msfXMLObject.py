import xml.etree.ElementTree as ET

class msfXmlObject(object):

    def __init__(self, fileName):
        self.tree = ET.parse(fileName)
        self.root = tree.getroot()
    
    def find_item(self, itemName):
        

