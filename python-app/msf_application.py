import sys
from PySide import QtGui,QtCore
from msfXMLObject import msfXmlObject
class MSF_Part_Identifier(QtGui.QWidget):
    
    def __init__(self):
        super(MSF_Part_Identifier, self).__init__()
        
        self.initUI()
        
    def initUI(self):
        self.xml_database = msfXmlObject('sample.xml')
        
        self.lbl = QtGui.QLabel('Part Code', self)
        self.lbl.move(10, 12)

        self.txt = QtGui.QLineEdit(self)
        self.txt.move(100, 10)
        
        self.but = QtGui.QPushButton('Retrieve Part', self)
        self.but.move(250, 10)        
        self.lstwid = QtGui.QListWidget(self)
        self.lstwid.move(10,30)
        
        def find_item(self):
            dict = self.xml_database.find_item(self.txt.text())
            self.lstwid.clear()
            if len(dict) > 1:
                
                for item in dict:
                    i = 0
                    if item != "empty":
                        temp_lab = QtGui.QListWidgetItem(item.attrib['name'])
                        self.lstwid.insertItem(i,temp_lab)
                        i = i + 1
        self.but.clicked.connect(lambda: find_item(self))
        
   
        self.setGeometry(300, 300, 400, 150)
        self.setWindowTitle('MSF Part Identifier')    
        self.show()
        
def main():
    
    app = QtGui.QApplication(sys.argv)
    ex = MSF_Part_Identifier()
    sys.exit(app.exec_())


if __name__ == '__main__':
    main()
