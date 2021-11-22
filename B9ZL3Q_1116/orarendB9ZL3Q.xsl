<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Szivos Adam orarend – 2021/22. I. felev.</h2>
                <table border="1">
                    <tr bgcolor="#85C406">
                        <th style="text-align:left">id</th>
                        <th style="text-align:left">tipus</th>
                        <th style="text-align:left">targy</th>
                        <th style="text-align:left">helyszin</th>
                        <th style="text-align:left">oktato</th>
                        <th style="text-align:left">szak</th>
                        <th style="text-align:left">nap</th>
                        <th style="text-align:left">tol</th>
                        <th style="text-align:left">ig</th>
                    </tr>
                    <xsl:for-each select="napok/hetfo/ora">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="@tipus"/></td>
                            <td><xsl:value-of select="targy"/></td>
                            <td><xsl:value-of select="helyszin"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="szak"/></td>
                            <td><xsl:value-of select="idopont/@nap"/></td>
                            <td><xsl:value-of select="idopont/@tol"/></td>
                            <td><xsl:value-of select="idopont/@ig"/></td>
                        </tr>
                    </xsl:for-each>
                    <xsl:for-each select="napok/kedd/ora">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="@tipus"/></td>
                            <td><xsl:value-of select="targy"/></td>
                            <td><xsl:value-of select="helyszin"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="szak"/></td>
                            <td><xsl:value-of select="idopont/@nap"/></td>
                            <td><xsl:value-of select="idopont/@tol"/></td>
                            <td><xsl:value-of select="idopont/@ig"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>