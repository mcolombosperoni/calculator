/**
 * Created by mcolombo.
 */
String.prototype.endsWith = function(str)
{
    var regexp = str + "$";
    return (this.match(regexp)==str)
};