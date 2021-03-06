package library.Sorts;

import java.util.Comparator;
import library.Item;

public class SortByTitleAsc implements Comparator<Item>
{
    @Override
    public int compare(Item o1, Item o2) {
        if (o1.getTitle().compareTo(o2.getTitle()) < 0)
        {
            return -1;
        }
        else if (o1.getTitle().compareTo(o2.getTitle()) > 0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}