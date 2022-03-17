Client defaultly shows the method priceDPH, which returns double. Tested, works.

Intermediary is supposed to count additional tax value. When the header comes, it is consumed. When the service returns tax value, the intermediary reads its value and counts additional tax which is included in header.

SOAP message:
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tut="http://tut01/">
   <soapenv:Header>
   	<taxFromAddedDirt xmlns:dph="http://dph.com/"/>
   </soapenv:Header>
   <soapenv:Body>
      <tut:priceDPH>
         <arg0>20228025</arg0>
         <arg1>10</arg1>
      </tut:priceDPH>
   </soapenv:Body>
</soapenv:Envelope>

How should the response look like:
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
    <S:Header>
        <dph:taxFromAddedDirt xmlns:dph="http://dph.com/">
            <dph:addedTax dph="17.45">Daň z přidané hlíny u tohoto produktu činí 17.45,-.</dph:addedTax>
        </dph:taxFromAddedDirt>
    </S:Header>
    <S:Body>
        <ns2:priceDPHResponse xmlns:ns2="http://tut01/">
         <return>34.9</return>
      </ns2:priceDPHResponse>
    </S:Body>
</S:Envelope>

Not tested, issues with running servlet in Eclipse.
