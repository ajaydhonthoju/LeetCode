class Solution {
    private static class Interval implements Comparable<Interval> {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public int compareTo(Interval other) {
            if (this.start != other.start) return Integer.compare(this.start, other.start);
            return Integer.compare(this.end, other.end);
        }
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return start == interval.start && end == interval.end;
        }
    }

    private static class Event implements Comparable<Event> {
        int y, type, xStart, xEnd;
        Event(int y, int type, int xStart, int xEnd) {
            this.y = y;
            this.type = type;
            this.xStart = xStart;
            this.xEnd = xEnd;
        }
        public int compareTo(Event other) {
            return Integer.compare(this.y, other.y);
        }
    }

    public double separateSquares(int[][] squares) {
        List<Event> events = new ArrayList<>();
        for (int[] sq : squares) {
            events.add(new Event(sq[1], 1, sq[0], sq[0] + sq[2]));
            events.add(new Event(sq[1] + sq[2], -1, sq[0], sq[0] + sq[2]));
        }
        Collections.sort(events);

        List<Interval> active = new ArrayList<>();
        List<double[]> strips = new ArrayList<>();

        double totalArea = 0;
        int prevY = events.get(0).y;

        for (Event e : events) {
            if (e.y > prevY) {
                double width = getUnionWidth(active);
                double height = e.y - prevY;
                if (width > 0) {
                    strips.add(new double[]{prevY, height, width});
                    totalArea += width * height;
                }
            }
            Interval iv = new Interval(e.xStart, e.xEnd);
            if (e.type == 1) active.add(iv);
            else active.remove(iv);
            prevY = e.y;
        }

        double target = totalArea / 2;
        double acc = 0;

        for (double[] strip : strips) {
            double area = strip[1] * strip[2];
            if (acc + area >= target) {
                return strip[0] + (target - acc) / strip[2];
            }
            acc += area;
        }
        return 0.0;
    }

    private double getUnionWidth(List<Interval> intervals) {
        if (intervals.isEmpty()) return 0;
        List<Interval> sorted = new ArrayList<>(intervals);
        Collections.sort(sorted);

        double width = 0, end = -1e18;
        for (Interval iv : sorted) {
            if (iv.start >= end) {
                width += iv.end - iv.start;
                end = iv.end;
            } else if (iv.end > end) {
                width += iv.end - end;
                end = iv.end;
            }
        }
        return width;
    }
}