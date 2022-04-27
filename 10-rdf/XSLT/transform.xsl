<?xml version="1.0" encoding="UTF-8" ?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="text" encoding="UTF-8" indent="no"/>
    <xsl:variable name="prefix">http://example.org/ClothingShop/userAccount/</xsl:variable>

    <xsl:template match="/">
        @prefix xsd: &lt;http://www.w3.org/2001/XMLSchema#&gt; .
        @prefix myvoc: &lt;http://example.org/ClothesShop/vocabulary&gt; .
        @prefix foaf: &lt;http://xmlns.com/foaf/0.1/&gt; .
        @prefix pext: &lt;http://www.ontotext.com/proton/protonext#&gt; .
        @prefix kees: &lt;http://linkeddata.center/kees/v1#&gt; .
        @prefix vcard: &lt;http://www.w3.org/2006/vcard/ns#&gt; .
        <xsl:apply-templates select=".//userAccount"/>
    </xsl:template>

    <xsl:template match="userAccount"> 
        <xsl:variable name="userURI" select="concat($prefix, id)"/>
        <xsl:variable name="givenName" select="substring-before(fullname,' ')"/>
        <xsl:variable name="familyName" select="substring-after(fullname,' ')"/>
        &lt;<xsl:value-of select="$userURI"/>&gt; a myvoc:Customer ;
            foaf:givenName &quot;<xsl:value-of select="$givenName"/>&quot;@en ;
            foaf:familyName &quot;<xsl:value-of select="$familyName"/>&quot;@en ;
            foaf:nick &quot;<xsl:value-of select="login"/>&quot;@en ;
            pext:Money &quot;<xsl:value-of select="balance"/>&quot;^^xsd:double ;
            kees:password &quot;<xsl:value-of select="password"/>&quot;^^xsd:string ;
            vcard:email &quot;<xsl:value-of select="email"/>&quot;^^xsd:string .
    </xsl:template>
    
</xsl:transform>