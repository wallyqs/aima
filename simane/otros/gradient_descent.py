xOld = 0
xNew = 6 # The algorithm starts at x=6
eps = 0.01 # step size
precision = 0.00001
 
def f_prime(x):
    return 4 * x**3 - 9 * x**2
 
while abs(xNew - xOld) > precision:
    xOld = xNew
    xNew = xNew - eps * f_prime(xNew)
    print xOld, xNew
    # print "Local minimum occurs at", xNew
