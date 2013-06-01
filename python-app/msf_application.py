import sys
from PySide import QtGui

class MSF_Part_Identifier(QtGui.QWidget):
    
    def __init__(self):
        super(MSF_Part_Identifier, self).__init__()
        
        self.initUI()
        
    def initUI(self):
        
        lbl1 = QtGui.QLabel('Part Code', self)
        lbl1.move(10, 12)

        lbl2 = QtGui.QLineEdit(self)
        lbl2.move(100, 10)
        
        lbl3 = QtGui.QPushButton('Retrieve Part', self)
        lbl3.move(250, 10)        
        
        self.setGeometry(300, 300, 400, 150)
        self.setWindowTitle('MSF Part Identifier')    
        self.show()
        
def main():
    
    app = QtGui.QApplication(sys.argv)
    ex = MSF_Part_Identifier()
    sys.exit(app.exec_())


if __name__ == '__main__':
    main()
