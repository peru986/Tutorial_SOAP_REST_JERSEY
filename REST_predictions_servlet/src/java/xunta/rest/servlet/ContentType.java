package xunta.rest.servlet;

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
