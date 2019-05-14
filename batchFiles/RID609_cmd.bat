set projectLocation=C:\Users\sathish_kumar\workspace\TurboPro
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\xmlFile\runRID609.xml
pause