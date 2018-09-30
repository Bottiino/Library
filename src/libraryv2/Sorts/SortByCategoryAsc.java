package libraryv2.Sorts;

import java.util.Comparator;
import libraryv2.Item;

public class SortByCategoryAsc implements Comparator<Item>
{
    @Override
    public int compare(Item o1, Item o2) {
        if (o1.getCategory().compareTo(o2.getCategory()) < 0)
        {
            return -1;
        }
        else if (o1.getCategory().compareTo(o2.getCategory()) > 0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}