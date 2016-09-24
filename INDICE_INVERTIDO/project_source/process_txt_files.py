
import re
from glob import glob

def parseTexts(fileglob='G:/TEC/tablas1/*txt'):
    
        texts, words = {}, set()
        
        for txtFile in glob(fileglob):
            
                with open(txtFile, 'r') as f:
                    
                        txt = re.findall(r'\S+\b',f.read().lower())
                        words |= set(txt)
                        texts[txtFile.split('\\')[-1]] = txt
                        
        return texts, words
