import requests
import json
import sys 
import numpy as np
from datetime import datetime
import matplotlib.pyplot as plt
import matplotlib.dates as mdates

reload(sys)  
sys.setdefaultencoding('utf8')

next_url =  'https://api.github.com/repos/phadej/github/commits'
params = {'access_token': 'cecdbb20512366e583861e38484e57f756dbf4eb'}
commitsArray = []
amountOfCommits = 0
Y = []
X = []
while next_url and amountOfCommits < 50:
    response = requests.get(next_url, params = params)
    decoded_r = response.content.decode()
    response_j = json.loads(decoded_r)
    
    for commit in response_j:
        date_string = commit['commit']['author']['date']
        datetime_object = datetime.strptime(date_string, '%Y-%m-%dT%H:%M:%SZ').date()
        
        commitsArray[datetime_object] = amountOfCommits
        amountOfCommits+=1
       
    
    if 'next' in response.links:
        next_url = response.links['next']['url']
    else:
        next_url = ''

y = range(len(x))
    #u'2016-01-21T15:45:13Z'

#Sort map by key
#Seperate into two arrays, x = date, y = nocommits
plt.plot(x,y, '-b')
plt.show()