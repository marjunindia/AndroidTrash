package com.example.arjun_mu.android;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NONE = 2;
    public int mRadioButtonChoice = NONE;

    onFragmentListener mListener;


    public SimpleFragment() {
        // Required empty public constructor
    }


    public static SimpleFragment newInstance() {

        Bundle args = new Bundle();

        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onFragmentListener) {

            mListener = (onFragmentListener) context;

        } else {
            throw new ClassCastException(context.toString()
                    + getResources().getString(R.string.exception_message));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        final View rootView =
                inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);

        // TODO: Set the radioGroup onCheckedChanged listener.
        radioGroup.setOnCheckedChangeListener(new
                                                      RadioGroup.OnCheckedChangeListener() {
                                                          @Override
                                                          public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                              View radioButton = radioGroup.findViewById(checkedId);
                                                              int index = radioGroup.indexOfChild(radioButton);
                                                              TextView textView =
                                                                      rootView.findViewById(R.id.fragment_header);
                                                              switch (index) {
                                                                  case YES: // User chose "Yes."
                                                                      textView.setText(R.string.yes_message);
                                                                      mRadioButtonChoice = YES;
                                                                      mListener.onRadioButtonChoice(YES);
                                                                      break;
                                                                  case NO: // User chose "No."
                                                                      textView.setText(R.string.no_message);
                                                                      mRadioButtonChoice = NO;
                                                                      mListener.onRadioButtonChoice(NO);
                                                                      break;
                                                                  default: // No choice made.
                                                                      mRadioButtonChoice = NONE;
                                                                      mListener.onRadioButtonChoice(NONE);
                                                                      break;
                                                              }
                                                          }
                                                      });
        // Return the View for the fragment's UI.
        return rootView;
    }

    interface onFragmentListener {
        void onRadioButtonChoice(int choice);
    }

}
