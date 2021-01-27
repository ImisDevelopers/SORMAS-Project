package de.symeda.sormas.ui.utils;

import org.junit.Test;

/**
 * @see ShowDetailsListener
 */
public class ShowDetailsListenerTest {

	@Test
	@SuppressWarnings({
		"rawtypes",
		"unchecked" })
	public void testItemClickDoesNotCallHandlerWhenItemIsNull() {
		/*
		 * Consumer<ContactIndexDto> handler = mock(Consumer.class);
		 * String detailsColumnId = ContactIndexDto.UUID;
		 * ShowDetailsListener<ContactIndexDto> cut = new ShowDetailsListener<>(detailsColumnId, handler);
		 * ItemClick<ContactIndexDto> event = mock(ItemClick.class);
		 * ContactIndexDto item = mock(ContactIndexDto.class);
		 * // 1a. ColumnClick
		 * Column column = mock(Column.class);
		 * when(event.getColumn()).thenReturn(column);
		 * when(column.getId()).thenReturn(detailsColumnId);
		 * cut.itemClick(event);
		 * verify(handler, never()).accept(null);
		 * // 1b. ColumnClick doing it when item is set
		 * when(event.getItem()).thenReturn(item);
		 * cut.itemClick(event);
		 * verify(handler).accept(item);
		 * reset(handler, event);
		 * // 2a. DoubleClick
		 * MouseEventDetails mouseEventDetails = mock(MouseEventDetails.class);
		 * when(event.getMouseEventDetails()).thenReturn(mouseEventDetails);
		 * when(mouseEventDetails.isDoubleClick()).thenReturn(true);
		 * cut.itemClick(event);
		 * verify(handler, never()).accept(null);
		 * // 2b. DoubleClick doing it when item is set
		 * when(event.getItem()).thenReturn(item);
		 * cut.itemClick(event);
		 * verify(handler).accept(item);
		 */
	}
}
