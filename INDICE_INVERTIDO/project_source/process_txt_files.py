
import re
from glob import glob

def parseTexts(fileglob='G:/TEC/tablas1/*txt'):
    
        texts, words = {}, set()
        documento_t = {}
        
        for txtFile in glob(fileglob):
            
                with open(txtFile, 'r') as f:
                    
                        txt = re.findall(r'([-À-ÿa-zA-Z0-9]+)',f.read().lower())
                        words |= set(txt)

                        docId = txtFile.split('\\')[-1]
                        documento_t[docId] = txtFile
                        texts[docId] = txt

                        
        return documento_t, texts, words
