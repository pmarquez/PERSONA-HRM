#   GLOBAL COUNT OF SENSOR USAGE (LAST 24 HOURS)

SELECT IFNULL(hei_sensorentity.sensorName,'') AS SENSOR_NAME, 
       COUNT(hei_evententity.sensorTagCode) AS CONTEO_USOS, 
       IFNULL(hei_evententity.sensorTagCode,'') AS SENSOR_TAG_CODE
        
FROM hei_evententity 

LEFT OUTER JOIN hei_sensorentity ON hei_sensorentity.sensorTagCode = hei_evententity.sensorTagCode

WHERE TIMESTAMP >= DATE_SUB(NOW(), INTERVAL 1 DAY) 

GROUP BY hei_evententity.sensorTagCode 

ORDER BY SENSOR_NAME;


#   COUNT OF USES OF SENSOR PER HOUR (LAST 24 HOURS)

SELECT IFNULL(hei_sensorentity.sensorName,'') AS SENSOR_NAME, 
       HOUR(TIMESTAMP) AS HOUR, 
       COUNT(*) AS COUNT 
       
FROM hei_evententity 

LEFT OUTER JOIN hei_sensorentity ON hei_sensorentity.sensorTagCode = hei_evententity.sensorTagCode

WHERE TIMESTAMP >= DATE_SUB(NOW(), INTERVAL 1 DAY)

GROUP BY HOUR(TIMESTAMP), hei_sensorentity.sensorName 

ORDER BY SENSOR_NAME, TIMESTAMP;

