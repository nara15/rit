
import math

def calculate_weight(freq, ni):

    a = 1 + math.log(freq,2)

    b = math.log((711/ni),2) #711

    return round(a * b,3)
