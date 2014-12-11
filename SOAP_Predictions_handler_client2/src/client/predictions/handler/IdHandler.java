package client.predictions.handler;

import client.predictions.Delete;
import client.predictions.Edit;
import client.predictions.GetOne;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.ws.LogicalMessage;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

public class IdHandler implements LogicalHandler<LogicalMessageContext>
{
    @Override
    public boolean handleMessage( LogicalMessageContext context )
    {
        boolean outbound = (Boolean) context.get( MessageContext.MESSAGE_OUTBOUND_PROPERTY );
        if ( outbound ) {
            LogicalMessage msg = context.getMessage();
            try {
                JAXBContext jaxbCtx = JAXBContext.newInstance( "client.predictions" );
                Object payload = msg.getPayload( jaxbCtx );
                if ( payload instanceof JAXBElement ) {
                    Object value = ( (JAXBElement) payload ).getValue();

                    int id = 0;
                    boolean getOne = false, edit = false, delete = false;
                    if ( value.toString().contains( "GetOne" ) ) {
                        getOne = true;
                        id = ( (GetOne) value ).getArg0();
                    }
                    else if ( value.toString().contains( "Edit" ) ) {
                        edit = true;
                        id = ( (Edit) value ).getArg0();
                    }
                    else if ( value.toString().contains( "GetOne" ) ) {
                        delete = true;
                        id = ( (Delete) value ).getArg0();
                    }
                    else {
                        return true;
                    }

                    if ( id > 0 ) {
                        return true;
                    }

                    if ( id == 0 ) {
                        throw new RuntimeException( "id no puede ser 0" );
                    }

                    int newId = -id;

                    if ( getOne ) {
                        ( (GetOne) value ).setArg0( newId );
                    }
                    else if ( edit ) {
                        ( (Edit) value ).setArg0( newId );
                    }
                    else if ( delete ) {
                        ( (Delete) value ).setArg0( newId );

                    }
                    ( (JAXBElement) payload ).setValue( value );
                    msg.setPayload( payload, jaxbCtx );
                }
            }
            catch (Exception e) {
                throw new RuntimeException( e );
            }

        }
        return true;
    }

    @Override
    public boolean handleFault( LogicalMessageContext context )
    {
        return true;
    }

    @Override
    public void close( MessageContext context )
    {
    }
}
