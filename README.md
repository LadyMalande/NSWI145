# NSWI145

## Homework progress

**01-business-process:** uploaded in repo

**02-soap:** uploaded in repo

  2 web services:
  
    1) Send mail with order number to given recipient for given order number
    
        Update: Second method is Send mail with PDF receipt to given recipient for given order number
    
    2) Create PDF file receipt with information about the order and return local path to the created PDF file
    
        Update: Second method is to count DPH value for given order number (price is searched for in database by given order number)

**03-wsdl:** uploaded 2/2 in repo

ReceiptWSDL: **DONE**, tested
        
MailWSDL: **DONE**, tested

**04-intermediary:**

**05-cxf:** **DONE**, tested in Eclipse and SOAPUI.

1) CXF Client for Receipt Web Service

2) **NEW** CXF Web Service for retrieving account balance by account name. Authentization with WS-Security plain text.

**06-uddi:** uploaded in repo.

  **NEW** sevice: Associate Discount Service

**07-bpel:** uploaded in repo.

  New service MyPDFMaker takes 5 parameters. It multiplies 2 numbers with the same multiplier in paralell. Then it adds the results of multiplying. At the same time, the tax is counted and PDF is made simultaneously. 
  Multiplications and sum are dependent, (multiplications-sum)/tax computation/PDF making are independent.

**08-rest:**

**09-lov:**

**10-rdf:**

