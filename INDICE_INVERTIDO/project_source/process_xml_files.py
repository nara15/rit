
# Library for processing XML files
import xml.etree.ElementTree as ET
# Library for reading files sequentially
from glob import glob
# Library for using regular expressions over text
import re

"""
    This functions parses every XML file from the repository and retrieves
    the needed parts of the files to index.
    It returns
               * a dict of documents and its content
               * a set of all the text found in documents
"""

def parseXML_Files(fileglob='G:/TEC/Flora-20160122/*.xml'):
    
    ns='http://www.github.com/inbio'
    
    documents, words = {}, set()
    
    for xmlFile in glob(fileglob):
        
        tree = ET.parse(xmlFile)
        
        taxon_identification = tree.find("{%s}taxon_identification" %(ns)).attrib
        
        description = tree.find("{%s}description" %(ns)).attrib
        
        des = description.get("taxon_description")
        texto = re.findall(r'\S+\b', des.lower())
        texto.append(taxon_identification.get("rank"))
        texto.append(taxon_identification.get("taxon_name"))
        
        words |= set(texto)
        
        documents[xmlFile.split('\\')[-1]] = texto

    return documents, words

documents, words = parseXML_Files()
