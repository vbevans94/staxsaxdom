<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<style>
					th {
						background: #9acd32;
					}
				</style>
			</head>
			<body>
				<h2>Gems</h2>
				<table border="1">
					<tr>
						<th rowspan="2" >Name</th>
						<th rowspan="2" >Presiousness</th>
						<th rowspan="2" >Origin</th>
						<th rowspan="2" >Value</th>
						<th colspan="3">VisualParams</th>
					</tr>
					<tr>
						<th>Color</th>
						<th>Transparency</th>
						<th>Corners</th>
					</tr>
					<xsl:for-each select="gems/gem">
						<tr>
							<td><xsl:value-of select="name"/></td>
							<td><xsl:value-of select="preciousness"/></td>
							<td><xsl:value-of select="origin"/></td>
							<td><xsl:value-of select="value"/></td>
							<td><xsl:value-of select="visual-params/color"/></td>
							<td><xsl:value-of select="visual-params/transparency"/></td>
							<td><xsl:value-of select="visual-params/corners"/></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
