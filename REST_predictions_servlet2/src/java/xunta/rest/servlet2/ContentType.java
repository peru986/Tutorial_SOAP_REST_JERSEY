package xunta.rest.servlet2;

public enum ContentType
{
    XML( "application/xml" ),
    JSON( "application/json" );

    private final String contentType;

    private ContentType( String contentType )
    {
        this.contentType = contentType;
    }

    public String getContentType()
    {
        return contentType;
    }
}
