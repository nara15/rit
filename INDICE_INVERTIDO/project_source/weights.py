
import math

def calculate_weight(freq, ni):

    a = 1 + math.log(freq,2)

    b = math.log(4/ni) #711

    return round(a * b,3)
