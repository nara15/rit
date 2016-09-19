

import xml.etree.ElementTree as ET
import glob

# Read XML from file
url = 'G:/TEC\Flora-20160122/10-Quercus salicifolia Nee.xml'
tree = ET.parse(url)
# Getting the elements from XML
ns='http://www.github.com/inbio'
taxon_identification = tree.find("{%s}taxon_identification" %(ns)).attrib
description = tree.find("{%s}description" %(ns)).attrib
# Getting specific XML elements
print(taxon_identification.get("rank"))
print(taxon_identification.get("taxon_name"))
print(description.get("taxon_description"))



# Tracing all xml in repository
"""
for x in glob.glob("C:/Users/jonaranjo/OneDrive/TEC/RIT/Flora-20160122/*.xml"):

	tree = ET.parse(x)
	root = tree.getroot()

	print(root.tag)
"""
